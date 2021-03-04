package kz.ktu.touroperator.service;

import kz.ktu.touroperator.model.TextForUser;
import kz.ktu.touroperator.repository.TextForUserRepository;
import kz.ktu.touroperator.repository.TextRepository;
import kz.ktu.touroperator.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class TextService {
    private final TextRepository textRepository;
    private final UserRepository userRepository;
    private final TextForUserRepository textForUserRepository;

    public TextService(TextRepository textRepository, UserRepository userRepository, TextForUserRepository textForUserRepository) {
        this.textRepository = textRepository;
        this.userRepository = userRepository;
        this.textForUserRepository = textForUserRepository;
    }

    public void sendText(Long user_id, Long text_id, String text){
        TextForUser textForUser = new TextForUser();
        textForUser.setText(text);
        textForUser.setUser(userRepository.getOne(user_id));
        textForUser.setTextForAdmin(textRepository.getOne(text_id));
        textForUserRepository.save(textForUser);
    }
}
