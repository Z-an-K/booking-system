package com.dxc.meetingroomreservationsystem.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
@Data
public class ExtendedProps implements Serializable {
    private static final long serialVersionUID = 5614263158176077768L;
    private String username;
    private String roomNumber;
    private String details;
    private Date registerTime;
    private String location;
    private Boolean  recurrence;
    private Integer recurrencePattern;
    private Date from;
    private Date to;
    private List<Integer> week;
    private Integer weekth;
    private Integer weekType;
    private int eventId;
    private String groupId;

    private ExtendedProps(Builder builder) {
        this.username = builder.username;
        this.roomNumber = builder.roomNumber;
        this.details = builder.details;
        this.registerTime = builder.registerTime;
        this.location = builder.location;
        this.recurrence = builder.recurrence;
        this.recurrencePattern = builder.recurrencePattern;
        this.from = builder.from;
        this.to = builder.to;
        this.week = builder.week;
        this.weekth = builder.weekth;
        this.weekType = builder.weekType;
        this.groupId = builder.groupId;
        this.eventId = builder.eventId;

    }

    public static class Builder {
        private String username;
        private String roomNumber;
        private String details;
        private Date registerTime;
        private String location;
        private Boolean recurrence;
        private Integer recurrencePattern;
        private Date from;
        private Date to;
        private List<Integer> week;
        private Integer weekth;
        private Integer weekType;
        private int eventId;

        private String groupId;

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder roomNumber(String roomNumber) {
            this.roomNumber = roomNumber;
            return this;
        }

        public Builder details(String details) {
            this.details = details;
            return this;
        }

        public Builder registerTime(Date registerTime) {
            this.registerTime = registerTime;
            return this;
        }

        public Builder location(String location) {
            this.location = location;
            return this;
        }

        public Builder recurrence(Boolean recurrence) {
            this.recurrence = recurrence;
            return this;
        }

        public Builder recurrencePattern(Integer recurrencePattern) {
            this.recurrencePattern = recurrencePattern;
            return this;
        }

        public Builder from(Date from) {
            this.from = from;
            return this;
        }

        public Builder to(Date to) {
            this.to = to;
            return this;
        }

        public Builder week(List<Integer> week) {
            this.week = week;
            return this;
        }

        public Builder weekth(Integer weekth) {
            this.weekth = weekth;
            return this;
        }

        public Builder weekType(Integer weekType) {
            this.weekType = weekType;
            return this;
        }

        public Builder eventId(int eventId){
            this.eventId = eventId;
            return this;
        }

        public Builder groupId(String groupId){
            this.groupId = groupId;
            return this;
        }

        public ExtendedProps build() {
            return new ExtendedProps(this);
        }
    }
}

