package hibernate.model;

import java.util.Objects;

public class CinemaHall {
    private Long id;
    private int capacity;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "CinemaHall{"
                + "id=" + id
                + ", capacity=" + capacity
                + ", description='"
                + description + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CinemaHall that = (CinemaHall) o;
        return capacity == that.capacity && Objects.equals(id, that.id)
                && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, capacity, description);
    }
}
