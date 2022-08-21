package com.kr.board.domain.member.mapper;

import com.kr.board.domain.member.dto.MemberRequestDTO;
import com.kr.board.domain.member.entity.Member;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-21T16:52:06+0900",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 1.8.0_332 (Azul Systems, Inc.)"
)
@Component
public class MemberMapperImpl implements MemberMapper {

    @Override
    public MemberRequestDTO toDto(Member entity) {
        if ( entity == null ) {
            return null;
        }

        MemberRequestDTO.MemberRequestDTOBuilder memberRequestDTO = MemberRequestDTO.builder();

        memberRequestDTO.id( entity.getId() );
        memberRequestDTO.email( entity.getEmail() );
        memberRequestDTO.nickname( entity.getNickname() );
        memberRequestDTO.password( entity.getPassword() );

        return memberRequestDTO.build();
    }

    @Override
    public Member toEntity(MemberRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Member.MemberBuilder member = Member.builder();

        member.id( dto.getId() );
        member.email( dto.getEmail() );
        member.nickname( dto.getNickname() );
        member.password( dto.getPassword() );

        return member.build();
    }

    @Override
    public List<MemberRequestDTO> toDto(List<Member> e) {
        if ( e == null ) {
            return null;
        }

        List<MemberRequestDTO> list = new ArrayList<MemberRequestDTO>( e.size() );
        for ( Member member : e ) {
            list.add( toDto( member ) );
        }

        return list;
    }

    @Override
    public List<Member> toEntity(List<MemberRequestDTO> d) {
        if ( d == null ) {
            return null;
        }

        List<Member> list = new ArrayList<Member>( d.size() );
        for ( MemberRequestDTO memberRequestDTO : d ) {
            list.add( toEntity( memberRequestDTO ) );
        }

        return list;
    }

    @Override
    public void updateFromDto(MemberRequestDTO dto, Member entity) {
        if ( dto == null ) {
            return;
        }
    }
}
