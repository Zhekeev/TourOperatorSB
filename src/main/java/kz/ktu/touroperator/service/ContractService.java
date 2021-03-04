package kz.ktu.touroperator.service;

import kz.ktu.touroperator.model.Contract;
import kz.ktu.touroperator.model.User;
import kz.ktu.touroperator.repository.ContractRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractService {
    private final ContractRepository contractRepository;

    public ContractService(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    public List<Contract> getUserAllContract(User user){
        return contractRepository.findByUser(user);
    }
}
