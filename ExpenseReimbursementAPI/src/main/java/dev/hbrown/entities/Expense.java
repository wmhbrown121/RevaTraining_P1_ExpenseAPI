package dev.hbrown.entities;


import javax.persistence.*;

@Entity
@Table(name="expense")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="expense_id")
    private int expenseId;

    @Column(name="amount")
    private double amount;

    @Column(name="reason")
    private String reason;

    @Column(name="date_submitted")
    private long dateSubmitted;

    @Column(name="status")
    private String status;

    @Column(name="date_verified")
    private long dateVerified;

    @Column(name="employee_id")
    private int employeeId;

    public Expense() {
    }

    public Expense(int expenseId, double amount, String reason, long dateSubmitted, String status, long dateVerified, int employeeId) {
        this.expenseId = expenseId;
        this.amount = amount;
        this.reason = reason;
        this.dateSubmitted = dateSubmitted;
        this.status = status;
        this.dateVerified = dateVerified;
        this.employeeId = employeeId;
    }

    public int getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(int expenseId) {
        this.expenseId = expenseId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public long getDateSubmitted() {
        return dateSubmitted;
    }

    public void setDateSubmitted(long dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getDateVerified() {
        return dateVerified;
    }

    public void setDateVerified(long dateVerified) {
        this.dateVerified = dateVerified;
    }

    public int getEmployeeId() { return employeeId; }

    public void setEmployeeId(int employeeId) { this.employeeId = employeeId; }

    @Override
    public String toString() {
        return "Expense{" +
                "expenseId=" + expenseId +
                ", amount=" + amount +
                ", reason='" + reason + '\'' +
                ", dateSubmitted=" + dateSubmitted +
                ", status='" + status + '\'' +
                ", dateVerified=" + dateVerified +
                ", employeeId=" + employeeId +
                '}';
    }
}
