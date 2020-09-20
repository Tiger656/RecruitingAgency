package com.itechart.agency.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
public class EmployerDto {
    private Long id;

    //зачем ему имя?
    private String name;
    private Long userId;
    //стоит убрать
    private Long agencyId;
    private Long cityId;
    private Long addressId;
    private Long employerContractId;
    private List<Long> applicationsIds;

    public static EmployerDto.Builder builder() {
        return new EmployerDto().new Builder();
    }

    public class Builder {
        public Builder() {
        }

        public EmployerDto.Builder withId(final Long id) {
            EmployerDto.this.setId(id);
            return this;
        }

        public EmployerDto.Builder withName(final String name) {
            EmployerDto.this.name = name;
            return this;
        }

        public EmployerDto.Builder withUserId(final Long userId) {
            EmployerDto.this.userId = userId;
            return this;
        }

        public EmployerDto.Builder withAgencyId(final Long agencyId) {
            EmployerDto.this.agencyId = agencyId;
            return this;
        }

        public EmployerDto.Builder withCityId(final Long cityId) {
            EmployerDto.this.cityId = cityId;
            return this;
        }

        public EmployerDto.Builder withAddressId(final Long addressId) {
            EmployerDto.this.addressId = addressId;
            return this;
        }

        public EmployerDto.Builder withEmployerContractId(final Long employerContractId) {
            EmployerDto.this.employerContractId = employerContractId;
            return this;
        }

        public EmployerDto.Builder withApplicationsIds(final List<Long> applicationsIds) {
            EmployerDto.this.applicationsIds = applicationsIds;
            return this;
        }

        public EmployerDto build() {
            return EmployerDto.this;
        }
    }

    @Override
    public String toString() {
        return "EmployerDto{" +
                "name='" + name + '\'' +
                ", userId=" + userId +
                ", agencyId=" + agencyId +
                ", cityId=" + cityId +
                ", addressId=" + addressId +
                ", employerContractId=" + employerContractId +
                '}';
    }
}
