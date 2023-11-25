package com.park.muscle.core.uniquetag;

import com.park.muscle.core.trainer.domain.Trainer;
import com.park.muscle.core.uniquetag.domain.UniqueTag;
import com.park.muscle.core.uniquetag.domain.UniqueTagRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UniqueTagService {
    private final UniqueTagRepository uniqueTagRepository;

    public String removeHash(String clientTag) {
        return clientTag.replace("#", "");
    }

    public Trainer getTrainerByTagId(final String tagId) {
        long withoutHash = Long.parseLong(removeHash(tagId));
        UniqueTag uniqueTag = uniqueTagRepository.findById(withoutHash).orElseThrow();
        return uniqueTag.getTrainer();
    }
}
