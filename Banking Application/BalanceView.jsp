<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Balance View Page</title>
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

        .balance-container {
            background-color: rgba(255, 255, 255, 0.1);
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.2);
        }

        h1 {
            font-size: 36px;
            margin-bottom: 20px;
            color: #fff;
        }

        p {
            font-size: 24px;
            font-weight: bold;
            color: #ffeb3b;
        }
    </style>
</head>
<body>
    <div class="balance-container">
        <h1>Your Current Balance</h1>
        <p>
            <%
                session = request.getSession();
                out.println(session.getAttribute("bal"));
            %>
        </p>
    </div>
</body>
</html>
