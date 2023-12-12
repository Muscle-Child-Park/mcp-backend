package com.park.muscle.core.uniquetag;

import com.park.muscle.core.member.domain.Member;
import com.park.muscle.core.trainer.domain.Trainer;
import com.park.muscle.core.uniquetag.domain.UniqueTag;
import com.park.muscle.core.uniquetag.domain.UniqueTagRepository;
import com.park.muscle.core.uniquetag.exception.TagNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UniqueTagService {
    private final UniqueTagRepository uniqueTagRepository;

    public String removeHash(String clientTag) {
        return clientTag.replace("#", "");
    }

    public Trainer findTrainerByTagId(final String tagId) {
        Long withoutHash = Long.parseLong(removeHash(tagId));
        UniqueTag uniqueTag = uniqueTagRepository.findById(withoutHash).orElseThrow(TagNotFoundException::new);
        return uniqueTag.getTrainer();
    }

    public Member findMemberByMemberTagId(final String memberTagId) {
        Long withoutHash = Long.parseLong(removeHash(memberTagId));
        UniqueTag uniqueTag = uniqueTagRepository.findById(withoutHash).orElseThrow(TagNotFoundException::new);
        return uniqueTag.getMember();
    }
}
