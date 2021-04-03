package kz.ktu.touroperator.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import kz.ktu.touroperator.model.Contract;
import kz.ktu.touroperator.model.User;
import kz.ktu.touroperator.repository.ContractRepository;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

@Service
public class ContractService {
    private final ContractRepository contractRepository;

    public ContractService(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    public List<Contract> getUserAllContract(User user){
        return contractRepository.findByUserOrderByPurchaseDateDesc(user);
    }
}
