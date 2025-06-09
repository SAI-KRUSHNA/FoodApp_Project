<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order Failed</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background-color: #fef2f2;
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
            color: #e53935;
            font-size: 32px;
            margin-bottom: 20px;
        }

        p {
            font-size: 18px;
            color: #666;
            margin-bottom: 30px;
        }

        .btn-retry {
            text-decoration: none;
            background-color: #f44336;
            color: white;
            padding: 12px 25px;
            border-radius: 6px;
            font-weight: bold;
            transition: background-color 0.3s ease;
        }

        .btn-retry:hover {
            background-color: #c62828;
        }

        .crossmark {
            font-size: 60px;
            color: #e53935;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>

    <div class="container">
        <div class="crossmark">✖</div>
        <h1>Order Failed</h1>
        <p>Something went wrong while processing your order. Please try again later.</p>
        <a href="cart.jsp" class="btn-retry">Go Back to Cart</a>
    </div>

</body>
</html>
