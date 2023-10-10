package com.park.muscle.mock;

import static com.park.muscle.core.member.domain.SocialType.KAKAO;

import com.park.muscle.core.member.domain.Member;
import com.park.muscle.core.member.domain.Name;
import com.park.muscle.core.member.domain.Role;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/members/auth")
@Api(tags = "auth management system")
public class AuthController {

    @ApiOperation(value = "회원 등록", response = Member.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully registered a new member"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    @PostMapping("/register")
    public ResponseEntity<Member> registerMember(
            @ApiParam(value = "Member information to register", required = true) @RequestBody Member member) {

        return ResponseEntity.ok(member);
    }

    @ApiOperation(value = "소셜 로그인", response = Member.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully logged in as a member"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Member not found")
    })
    @PostMapping("/login")
    public ResponseEntity<Member> loginMember(
            @ApiParam(value = "User's social login provider (e.g., KAKAO, APPLE)", required = true)
            @RequestParam String socialProvider,
            @ApiParam(value = "User's social login ID (e.g., KAKAO UID, APPLE UID)", required = true)
            @RequestParam String socialLoginId) {
        // 예시: 카카오 소셜 로그인 처리
        if ("KAKAO".equalsIgnoreCase(socialProvider)) {
            Member member = new Member(KAKAO, socialLoginId, Name.from("홍길동"), Role.ROLE_MEMBER);
            return ResponseEntity.ok(member);
        }

        // 사용자를 찾지 못한 경우 404 Not Found 또는 401 Unauthorized 반환
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ApiOperation(value = "멤버 프로필 업데이트", response = Member.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Member profile updated successfully"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Member not found")
    })
    @PutMapping("/profile/{memberId}")
    public ResponseEntity<Member> updateMemberProfile(
            @ApiParam(value = "ID of the member to update profile", required = true) @PathVariable Long memberId,
            @ApiParam(value = "Updated member profile data", required = true) @RequestBody Member updatedMember) {

        return ResponseEntity.ok(updatedMember);
    }

    @ApiOperation(value = "멤버 계정 삭제")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Member account deleted successfully"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Member not found")
    })
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteOwnMemberAccount() {

        // 삭제가 성공한 경우 204 No Content 반환
        return ResponseEntity.noContent().build();
    }
}
