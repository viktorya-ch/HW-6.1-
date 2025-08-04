package com.example.demo.dynamic;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
public class RuleCondition {

    @Id
    @GeneratedValue
    private UUID id;


    private  String query;
    private String arguments;

    private boolean negate;

    @ManyToOne
    @JoinColumn
    private DynamicRule rule;

    public RuleCondition(UUID id, String query, String arguments, boolean negate) {
        this.id = id;
        this.query = query;
        this.arguments = arguments;
        this.negate = negate;
    }


    public UUID getId() {
        return id;
    }

    public String getQuery() {
        return query;
    }

    public String getArguments() {
        return arguments;
    }

    public boolean isNegate() {
        return negate;
    }


    public void setId(UUID id) {
        this.id = id;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public void setArguments(String arguments) {
        this.arguments = arguments;
    }

    public void setNegate(boolean negate) {
        this.negate = negate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RuleCondition that = (RuleCondition) o;
        return negate == that.negate && Objects.equals(id, that.id) && Objects.equals(query, that.query) && Objects.equals(arguments, that.arguments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, query, arguments, negate);
    }
}
