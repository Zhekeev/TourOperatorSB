package kz.ktu.touroperator.service;

import kz.ktu.touroperator.repository.TextRepository;
import kz.ktu.touroperator.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class TextService {
    private final TextRepository textRepository;
    private final UserRepository userRepository;

    public TextService(TextRepository textRepository, UserRepository userRepository) {
        this.textRepository = textRepository;
        this.userRepository = userRepository;
    }
}
