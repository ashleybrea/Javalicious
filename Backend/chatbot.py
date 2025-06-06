from dotenv import load_dotenv
import os
import openai
from openai import OpenAI


load_dotenv()
openai.api_key = os.getenv("OPENAI_API_KEY")
print("API Key Loaded:", openai.api_key is not None)

client = OpenAI()

user_data = {
    "firstName": "Jin",
    "lastName": "Kazama",
    "email": "jin.k@email.com",
    "phone": "555-0123",
    "memberSince": "2022-03-15",
    "subscriptionType": "Premium",
    "ficoScore": 602,
    "paymentHistory": "Excellent",
    "creditUtilization": "15%",
    "creditHistory": "8 years",
    "creditMix": "Good variety",
    "recentCreditInquiries": 0,
    "latePayments": 0
}


model_instructions = f"""
You are Max, a friendly and helpful AI financial advisor whose goal is to help users MAX-imize their credit score. 
Keep responses extremely concise (maximum 3 sentences) and action-oriented, while maintaining a warm, approachable tone.

Credit Score Components:
- Payment history (35%): Timely payments
- Credit utilization (30%): Amount of credit used vs available
- Length of credit history (15%): Age of accounts
- Credit mix (10%): Variety of credit types
- New credit inquiries (10%): Recent credit applications

User Profile:
- FICO Score: {user_data['ficoScore']}
- Payment History: {user_data['paymentHistory']}
- Credit Utilization: {user_data['creditUtilization']}
- Credit History: {user_data['creditHistory']}
- Credit Mix: {user_data['creditMix']}
- Recent Credit Inquiries: {user_data['recentCreditInquiries']}
- Late Payments: {user_data['latePayments']}

When asked "How can I improve the score in this area?", assume the user is asking about Credit Mix/Number of Accounts.
Provide specific, actionable advice based on the user's data without recapping their information.
Focus on concrete steps to improve their credit score while maintaining a friendly, encouraging tone.
"""
# response = client.responses.create(
#     model="gpt-4.1",
#     input="Write a one-sentence bedtime story about a unicorn."
#
# )

response = client.chat.completions.create(
    model='gpt-4',
    messages=[
        {"role": "system", "content": model_instructions},
        {"role": "user", "content": "How can I improve the score in this area?"}
    ]
)


print(response.choices[0].message)
