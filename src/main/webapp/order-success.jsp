<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order Success</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            text-align: center;
        }

        .container {
            background-color: white;
            margin: 100px auto;
            padding: 40px;
            max-width: 500px;
            box-shadow: 0px 0px 15px rgba(0, 0, 0, 0.1);
            border-radius: 12px;
        }

        h1 {
            color: #28a745;
            font-size: 32px;
            margin-bottom: 20px;
        }

        p {
            font-size: 18px;
            color: #555;
            margin-bottom: 30px;
        }

        .btn-home {
            text-decoration: none;
            background-color: orange;
            color: white;
            padding: 12px 25px;
            border-radius: 6px;
            font-weight: bold;
            transition: background-color 0.3s ease;
        }

        .btn-home:hover {
            background-color: green;
        }

        .checkmark {
            font-size: 60px;
            color: #28a745;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>

    <div class="container">
        <div class="checkmark">✔</div>
        <h1>Thank You!</h1>
        <p>Your order has been placed successfully.</p>
        <a href="home" class="btn-home">Back to Home</a>
    </div>

</body>
</html>
