package com.fico.chatbot.config;

import com.fico.chatbot.model.Member;
import com.fico.chatbot.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * Data initializer - loads initial data when app starts
 * @Component tells Spring to create this as a bean
 */
@Component
public class DataInitializer implements ApplicationRunner {
    
    @Autowired
    private MemberRepository memberRepository;
    
    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Only load data if database is empty
        if (memberRepository.count() == 0) {
            loadInitialData();
            System.out.println("✅ Initial member data loaded successfully!");
        } else {
            System.out.println("📊 Database already contains data, skipping initialization.");
        }
    }
    
    private void loadInitialData() {
        // Jin Kazama - Premium member with excellent credit
        Member jin = new Member(
            0L,                    // id
            "Jin",                 // firstName
            "Kazama",              // lastName
            "jin.k@email.com",     // email
            "555-0123",            // phone
            "2022-03-15",          // memberSince
            "Premium",             // subscriptionType
            742,                   // ficoScore
            "Excellent",           // paymentHistory
            "15%",                 // creditUtilization
            "8 years",             // creditHistory
            "Good variety",        // creditMix
            0,                     // recentCreditInquiries
            0                      // latePayments
        );
        
        // Kazuya Mishima - Basic member with good credit but room for improvement
        Member kazuya = new Member(
            1L,                    // id
            "Kazuya",              // firstName
            "Mishima",             // lastName
            "m.chen@email.com",    // email
            "555-0456",            // phone
            "2023-01-20",          // memberSince
            "Basic",               // subscriptionType
            680,                   // ficoScore
            "Good",                // paymentHistory
            "35%",                 // creditUtilization
            "4 years",             // creditHistory
            "Ok variety",          // creditMix
            3,                     // recentCreditInquiries
            2                      // latePayments
        );
        
        // Save both members to database
        memberRepository.save(jin);
        memberRepository.save(kazuya);
        
        System.out.println("👤 Added Jin Kazama (FICO: 742, Premium)");
        System.out.println("👤 Added Kazuya Mishima (FICO: 680, Basic)");
    }
}