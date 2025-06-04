PRAGMA foreign_keys=OFF;
BEGIN TRANSACTION;
CREATE TABLE members (
    id INTEGER PRIMARY KEY,
    firstName TEXT,
    lastName TEXT,
    email TEXT,
    phone TEXT,
    memberSince TEXT,
    subscriptionType TEXT,
    ficoScore INTEGER,
    paymentHistory TEXT,
    creditUtilization TEXT,
    creditHistory TEXT,
    creditMix TEXT,
    recentCreditInquiries INTEGER,
    latePayments INTEGER
);
INSERT INTO members VALUES(0,'Jin','Kazama','jin.k@email.com','555-0123','2022-03-15','Premium',742,'Excellent','15%','8 years','Good variety',0,0);
INSERT INTO members VALUES(1,'Kazuya','Mishima','m.chen@email.com','555-0456','2023-01-20','Basic',680,'Good','35%','4 years','Ok variety',3,2);
COMMIT;
