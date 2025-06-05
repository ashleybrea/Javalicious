package com.fico.chatbot.controller;

import com.fico.chatbot.model.Member;
import com.fico.chatbot.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST Controller for Member API endpoints
 * @RestController tells Spring this handles HTTP requests
 * @RequestMapping sets the base URL path
 */
@RestController
@RequestMapping("/members")
@CrossOrigin(origins = "http://localhost:3000") // Allow frontend to connect
public class MemberController {
    
    @Autowired
    private MemberService memberService;
    
    /**
     * GET /api/members - Get all members
     * Example: http://localhost:8080/api/members
     */
    @GetMapping
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }
    
    /**
     * GET /api/members/{id} - Get member by ID
     * Example: http://localhost:8080/api/members/1
     */
    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable Long id) {
        Optional<Member> member = memberService.getMemberById(id);
        
        if (member.isPresent()) {
            return ResponseEntity.ok(member.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    /**
     * GET /api/members/email/{email} - Get member by email
     * Example: http://localhost:8080/api/members/email/jin.k@email.com
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<Member> getMemberByEmail(@PathVariable String email) {
        Optional<Member> member = memberService.getMemberByEmail(email);
        
        if (member.isPresent()) {
            return ResponseEntity.ok(member.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    /**
     * POST /api/members - Create new member
     * Expects Member JSON in request body
     */
    @PostMapping
    public Member createMember(@RequestBody Member member) {
        return memberService.saveMember(member);
    }
    
    /**
     * PUT /api/members/{id} - Update existing member
     * Expects Member JSON in request body
     */
    @PutMapping("/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable Long id, @RequestBody Member memberDetails) {
        Optional<Member> memberOpt = memberService.getMemberById(id);
        
        if (memberOpt.isPresent()) {
            Member member = memberOpt.get();
            // Update fields
            member.setFirstName(memberDetails.getFirstName());
            member.setLastName(memberDetails.getLastName());
            member.setEmail(memberDetails.getEmail());
            member.setPhone(memberDetails.getPhone());
            member.setSubscriptionType(memberDetails.getSubscriptionType());
            member.setFicoScore(memberDetails.getFicoScore());
            member.setPaymentHistory(memberDetails.getPaymentHistory());
            member.setCreditUtilization(memberDetails.getCreditUtilization());
            member.setCreditHistory(memberDetails.getCreditHistory());
            member.setCreditMix(memberDetails.getCreditMix());
            member.setRecentCreditInquiries(memberDetails.getRecentCreditInquiries());
            member.setLatePayments(memberDetails.getLatePayments());
            
            return ResponseEntity.ok(memberService.saveMember(member));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    /**
     * DELETE /api/members/{id} - Delete member
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMember(@PathVariable Long id) {
        if (memberService.getMemberById(id).isPresent()) {
            memberService.deleteMember(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    /**
     * GET /api/members/{id}/analysis - Get credit score analysis
     */
    @GetMapping("/{id}/analysis")
    public ResponseEntity<String> getCreditAnalysis(@PathVariable Long id) {
        String analysis = memberService.getCreditScoreAnalysis(id);
        return ResponseEntity.ok(analysis);
    }
    
    /**
     * GET /api/members/{id}/suggestions - Get improvement suggestions
     */
    @GetMapping("/{id}/suggestions")
    public ResponseEntity<String> getImprovementSuggestions(@PathVariable Long id) {
        String suggestions = memberService.getImprovementSuggestions(id);
        return ResponseEntity.ok(suggestions);
    }
}