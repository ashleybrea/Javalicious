from dotenv import load_dotenv
import os
import openai
from openai import OpenAI
import sqlite3


load_dotenv()
openai.api_key = os.getenv("OPENAI_API_KEY")
print("API Key Loaded:", openai.api_key is not None)

client = OpenAI()

def get_user_data(user_id):
    conn = sqlite3.connect('attempt1.db')
    cursor = conn.cursor()
    
    cursor.execute('''
        SELECT firstName, lastName, email, phone, memberSince, subscriptionType,
               ficoScore, paymentHistory, creditUtilization, creditHistory,
               creditMix, recentCreditInquiries, latePayments
        FROM members
        WHERE id = ?
    ''', (user_id,))
    
    row = cursor.fetchone()
    conn.close()
    
    if row:
        return {
            "firstName": row[0],
            "lastName": row[1],
            "email": row[2],
            "phone": row[3],
            "memberSince": row[4],
            "subscriptionType": row[5],
            "ficoScore": row[6],
            "paymentHistory": row[7],
            "creditUtilization": row[8],
            "creditHistory": row[9],
            "creditMix": row[10],
            "recentCreditInquiries": row[11],
            "latePayments": row[12]
        }
    return None

def get_available_users():
    conn = sqlite3.connect('attempt1.db')
    cursor = conn.cursor()
    
    cursor.execute('SELECT id, firstName, lastName FROM members')
    users = cursor.fetchall()
    conn.close()
    
    return users

def get_chatbot_response(messages):
    response = client.chat.completions.create(
        model='gpt-4',
        messages=messages
    )
    return response.choices[0].message.content

def main():
    # Show available users
    users = get_available_users()
    print("\nAvailable users:")
    for user in users:
        print(f"{user[0]}: {user[1]} {user[2]}")
    
    # Get user selection
    while True:
        try:
            user_id = int(input("\nEnter the ID of the user you want to chat with: "))
            user_data = get_user_data(user_id)
            if user_data:
                break
            print("Invalid user ID. Please try again.")
        except ValueError:
            print("Please enter a valid number.")
    
    # Create model instructions with selected user's data
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
    
    # Initialize conversation with system instructions and welcome message
    messages = [
        {"role": "system", "content": model_instructions},
        {"role": "assistant", "content": f"Hi {user_data['firstName']}! I'm Max, I'm here to help you MAX-imize your credit score. How can I help?"}
    ]
    
    print("\nMax:", messages[-1]["content"])
    
    while True:
        # Get user input
        user_input = input("\nYou: ").strip()
        
        # Check if user wants to end the conversation
        if user_input.lower() in ['bye', 'quit', 'stop', 'exit']:
            print("\nMax: Thanks for chatting! Remember, I'm here whenever you need help with your credit score. Have a great day!")
            break
        
        # Add user message to conversation
        messages.append({"role": "user", "content": user_input})
        
        # Get chatbot response
        response = get_chatbot_response(messages)
        
        # Add chatbot response to conversation
        messages.append({"role": "assistant", "content": response})
        
        # Print chatbot response
        print("\nMax:", response)

if __name__ == "__main__":
    main()
