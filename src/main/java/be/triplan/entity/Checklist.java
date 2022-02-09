package be.triplan.entity;

import be.triplan.dto.checklist.ChecklistDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Checklist {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "checklist_id")
    private Long id;

    private String checkItem;

    @Column(name = "is_selected")
    private Boolean isSelected;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "plan_id")
    private Plan plan;

    public void addPlan(Plan plan) {
        this.plan = plan;
        plan.getChecklists().add(this);
    }

    public void addCheckList(String checkItem, Boolean isSelected) {
        this.checkItem = checkItem;
        this.isSelected = isSelected;
    }

    public void updateCheckItem(String checkItem) {
        this.checkItem = checkItem;
    }

    public void updateIsSelected(Boolean isSelected) {
        this.isSelected = isSelected;
    }

    public static Checklist createCheckList(Plan plan, ChecklistDto checkListDto) {
        Checklist checklist = new Checklist();
        checklist.addPlan(plan);
        checklist.addCheckList(checkListDto.getCheckItem(), checkListDto.getIsSelected());

        return checklist;
    }
}
