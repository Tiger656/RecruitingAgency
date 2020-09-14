package com.itechart.agency.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Setter
@Getter
public class EmployerContractDto {
    private Long id;

    private Long contractTypeId;
    private Long fileId;
    private double daily_payment;
    private Date contract_creation_date;
    private Date contract_end_date;
    private boolean is_suspended;
    private boolean is_deleted;

    public static EmployerContractDto.Builder builder() {
        return new EmployerContractDto().new Builder();
    }

    public class Builder {
        private Builder() {
        }

        public EmployerContractDto.Builder withId(final Long id) {
            EmployerContractDto.this.setId(id);
            return this;
        }

        public EmployerContractDto.Builder withDailyPayment(final double dailyPayment) {
            EmployerContractDto.this.daily_payment = dailyPayment;
            return this;
        }

        public EmployerContractDto.Builder withContractCreationDate(final Date contractCreationDate) {
            EmployerContractDto.this.contract_creation_date = contractCreationDate;
            return this;
        }

        public EmployerContractDto.Builder withContractEndDate(final Date contractEndDate) {
            EmployerContractDto.this.contract_end_date = contractEndDate;
            return this;
        }

        public EmployerContractDto.Builder withFileId(final Long fileId) {
            EmployerContractDto.this.fileId = fileId;
            return this;
        }

        public EmployerContractDto.Builder withContractTypeId(final Long contractTypeId) {
            EmployerContractDto.this.contractTypeId = contractTypeId;
            return this;
        }

        public EmployerContractDto.Builder withIsSuspended(final boolean isSuspended) {
            EmployerContractDto.this.is_suspended = isSuspended;
            return this;
        }

        public EmployerContractDto.Builder withIsDeleted(final boolean isDeleted) {
            EmployerContractDto.this.is_deleted = isDeleted;
            return this;
        }

        public EmployerContractDto build() {
            return EmployerContractDto.this;
        }
    }

    @Override
    public String toString() {
        return "EmployerContractDto{" +
                "contractTypeId=" + contractTypeId +
                ", fileId=" + fileId +
                ", daily_payment=" + daily_payment +
                ", contract_creation_date=" + contract_creation_date +
                ", contract_end_date=" + contract_end_date +
                ", is_suspended=" + is_suspended +
                ", is_deleted=" + is_deleted +
                '}';
    }
}
