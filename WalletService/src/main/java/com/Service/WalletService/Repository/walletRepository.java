package com.Service.WalletService.Repository;

import com.Service.WalletService.Model.wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface walletRepository extends JpaRepository<wallet, Integer> {
}
