package com.itechart.agency.dto;


import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
public class EmployerApplicationDto {

    private Long id;

    private Long agencyId;
    private String application_number;
    private Date application_date;
    private Long employerId;
    private String application_name;
    private Long professionId;
    private double salary;
    private Long employmentTypeId;
    private String expert_personal_name;
    private Date creation_date;
    private Date end_date;
    private Long statusId;
    private List<Long> featuresIds;

    public static Builder builder() {
        return new EmployerApplicationDto().new Builder();
    }

    public class Builder {
        private Builder() {
        }

        public Builder withId(final Long id) {
            EmployerApplicationDto.this.setId(id);
            return this;
        }

        public Builder withAgencyId(final Long agencyId) {
            EmployerApplicationDto.this.agencyId = agencyId;
            return this;
        }

        public Builder withApplicationName(final String applicationName) {
            EmployerApplicationDto.this.application_name = applicationName;
            return this;
        }

        public Builder withProfessionId(final Long professionId) {
            EmployerApplicationDto.this.professionId = professionId;
            return this;
        }

        public Builder withSalary(final double salary) {
            EmployerApplicationDto.this.salary = salary;
            return this;
        }

        public Builder withEmploymentTypeId(final Long employmentTypeId) {
            EmployerApplicationDto.this.employmentTypeId = employmentTypeId;
            return this;
        }

        public Builder withExpertPersonalName(final String expertPersonalName) {
            EmployerApplicationDto.this.expert_personal_name = expertPersonalName;
            return this;
        }

        public Builder withCreationDate(final Date creationDate) {
            EmployerApplicationDto.this.creation_date = creationDate;
            return this;
        }

        public Builder withEndDate(final Date endDate) {
            EmployerApplicationDto.this.end_date = endDate;
            return this;
        }

        public Builder withStatusId(final Long statusId) {
            EmployerApplicationDto.this.statusId = statusId;
            return this;
        }

        public Builder withFeaturesIds(final List<Long> featuresIds) {
            EmployerApplicationDto.this.featuresIds = featuresIds;
            return this;
        }

        public EmployerApplicationDto build() {
            return EmployerApplicationDto.this;
        }
    }

    @Override
    public String toString() {
        return "EmployerApplicationDto{" +
                "agencyId=" + agencyId +
                ", application_number='" + application_number + '\'' +
                ", application_date=" + application_date +
                ", employerId=" + employerId +
                ", application_name='" + application_name + '\'' +
                ", professionId=" + professionId +
                ", salary=" + salary +
                ", employmentTypeId=" + employmentTypeId +
                ", expert_personal_name='" + expert_personal_name + '\'' +
                ", creation_date=" + creation_date +
                ", end_date=" + end_date +
                ", statusId=" + statusId +
                '}';
    }
}
