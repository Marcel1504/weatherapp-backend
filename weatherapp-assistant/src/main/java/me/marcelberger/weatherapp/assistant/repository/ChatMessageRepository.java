package me.marcelberger.weatherapp.assistant.repository;

import me.marcelberger.weatherapp.assistant.entity.ChatMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessageEntity, Long> {
}
