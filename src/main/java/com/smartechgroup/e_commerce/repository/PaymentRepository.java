package com.smartechgroup.e_commerce.repository;

import com.smartechgroup.e_commerce.model.Payment;
import com.smartechgroup.e_commerce.request.PaymentItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
