<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Loan Success Page</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background: linear-gradient(135deg, #4e73df, #1cc88a);
            color: #fff;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            text-align: center;
        }

        .message-container {
            background-color: rgba(255, 255, 255, 0.1);
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            max-width: 500px;
        }

        h1 {
            font-size: 28px;
            margin-bottom: 20px;
            color: #ffeb3b;
        }

        h3 {
            font-size: 22px;
            color: #fff;
            margin-top: 20px;
        }

        p {
            font-size: 18px;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
    <div class="message-container">
        <h1>Thank You!</h1>
        <p>
            <% 
                session = request.getSession(); 
                out.println("Dear " + session.getAttribute("name") + ", thank you for showing your interest in the loans from SBI Bank.");
            %>
        </p>
        <p>
            Our executive will contact you soon on your email address mentioned below:
        </p>
        <h3><% out.println(session.getAttribute("email")); %></h3>
    </div>
</body>
</html>
