package com.mohamed.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.HashSet;
import java.util.Set;

/**
 * A Classe.
 */
@Entity
@Table(name = "classe")
public class Classe implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JsonIgnoreProperties("classes")
    private User teacher;

    @OneToOne
    @JoinColumn(unique = true)
    private User studentArea;

    @OneToMany(mappedBy = "classe")
    private Set<File> files = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Classe name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getTeacher() {
        return teacher;
    }

    public Classe teacher(User user) {
        this.teacher = user;
        return this;
    }

    public void setTeacher(User user) {
        this.teacher = user;
    }

    public User getStudentArea() {
        return studentArea;
    }

    public Classe studentArea(User user) {
        this.studentArea = user;
        return this;
    }

    public void setStudentArea(User user) {
        this.studentArea = user;
    }

    public Set<File> getFiles() {
        return files;
    }

    public Classe files(Set<File> files) {
        this.files = files;
        return this;
    }

    public Classe addFile(File file) {
        this.files.add(file);
        file.setClasse(this);
        return this;
    }

    public Classe removeFile(File file) {
        this.files.remove(file);
        file.setClasse(null);
        return this;
    }

    public void setFiles(Set<File> files) {
        this.files = files;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Classe)) {
            return false;
        }
        return id != null && id.equals(((Classe) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Classe{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
