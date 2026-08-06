package com.arypay.wallet;


import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arypay.wallet.DTO.WalletDTO;

@RestController
@RequestMapping("/wallets")
public class WalletController {
    private final WalletRepository repository;

    public WalletController (WalletRepository walletRepository) {
        this.repository = walletRepository;
    }

    @GetMapping
    public List<WalletDTO> listAll() {
        return repository.findAll().stream().map(
            target -> new WalletDTO(target.getId(), target.getHolder(), target.getBalance()))
        .toList();
    }


    // @GetMapping public List <WalletDTO> listAll () {}

}
