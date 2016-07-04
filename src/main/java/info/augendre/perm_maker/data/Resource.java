package info.augendre.perm_maker.data;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.time.DayOfWeek;

/**
 * Created by gaugendre on 28/06/16
 */
public class Resource {
    private String name;
    private Planning availability;

    public Resource() {
        this.name = "";
        this.availability = new Planning();
    }

    public Resource(String name, Planning availability) {
        this.name = name;
        this.availability = availability;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Planning getAvailability() {
        return availability;
    }

    public void setAvailability(Planning availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return this.name + " : " + this.availability.getTasks();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Resource resource = (Resource) o;

        return new EqualsBuilder()
                .append(name, resource.name)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(name)
                .toHashCode();
    }

    public boolean isAvailableAt(Task task, DayOfWeek day) {
        for (Task t : this.availability.getTasksForDay(day)) {
            if (task.getStartTime().compareTo(t.getStartTime()) <= 0 && task.getEndTime().compareTo(t.getEndTime()) >= 0) {
                return true;
            }
        }
        return false;
    }
}
