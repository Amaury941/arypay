package com.arypay.wallet;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.arypay.wallet.DTO.WalletDTO;

@Controller
@RequestMapping("Wallets")
public class WalletController {
    private final WalletRepository repository;

    public WalletController(WalletRepository walletController) {
        this.repository = walletController; 
    }
    // @GetMapping public List <WalletDTO> listAll () {}

}
