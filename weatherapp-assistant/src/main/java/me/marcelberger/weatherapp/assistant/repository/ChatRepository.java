package me.marcelberger.weatherapp.assistant.repository;

import me.marcelberger.weatherapp.assistant.entity.ChatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends JpaRepository<ChatEntity, Long> {
}
