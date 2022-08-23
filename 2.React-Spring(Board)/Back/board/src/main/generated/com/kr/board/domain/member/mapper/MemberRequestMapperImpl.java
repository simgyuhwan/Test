package com.kr.board.domain.member.mapper;

import com.kr.board.domain.member.dto.MemberRequestDTO;
import com.kr.board.domain.member.entity.Member;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-23T12:32:37+0900",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 1.8.0_202 (Oracle Corporation)"
)
@Component
public class MemberRequestMapperImpl implements MemberRequestMapper {

    @Override
    public MemberRequestDTO toDto(Member arg0) {
        if ( arg0 == null ) {
            return null;
        }

        MemberRequestDTO.MemberRequestDTOBuilder memberRequestDTO = MemberRequestDTO.builder();

        memberRequestDTO.id( arg0.getId() );
        memberRequestDTO.email( arg0.getEmail() );
        memberRequestDTO.nickname( arg0.getNickname() );
        memberRequestDTO.password( arg0.getPassword() );

        return memberRequestDTO.build();
    }

    @Override
    public Member toEntity(MemberRequestDTO arg0) {
        if ( arg0 == null ) {
            return null;
        }

        Member.MemberBuilder member = Member.builder();

        member.id( arg0.getId() );
        member.email( arg0.getEmail() );
        member.nickname( arg0.getNickname() );
        member.password( arg0.getPassword() );

        return member.build();
    }

    @Override
    public List<MemberRequestDTO> toDto(List<Member> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<MemberRequestDTO> list = new ArrayList<MemberRequestDTO>( arg0.size() );
        for ( Member member : arg0 ) {
            list.add( toDto( member ) );
        }

        return list;
    }

    @Override
    public List<Member> toEntity(List<MemberRequestDTO> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<Member> list = new ArrayList<Member>( arg0.size() );
        for ( MemberRequestDTO memberRequestDTO : arg0 ) {
            list.add( toEntity( memberRequestDTO ) );
        }

        return list;
    }

    @Override
    public void updateFromDto(MemberRequestDTO arg0, Member arg1) {
        if ( arg0 == null ) {
            return;
        }
    }
}
