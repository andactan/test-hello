package application.repository;

import application.core.Topic;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TopicRepository extends CrudRepository<Topic, Integer> {

    List<Topic> findAll();
    Topic findById(Integer id);

    @Query("SELECT * FROM Relation r WHERE r.source = :sourceId")
    List<String> getRelations(@Param("sourceId") int sourceId);

    @Query("SELECT * FROM TagRelation tr WHERE tr.topicId = :topicId")
    List<String> getTags(@Param("topicId") int topicId);

    @Query("SELECT * FROM Post p WHERE p.topicId = :topicId")
    List<String> getPosts(@Param("topicId") int topicId);
}
