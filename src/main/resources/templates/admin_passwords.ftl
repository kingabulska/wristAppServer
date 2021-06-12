<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Zmien haslo</title>
  <style>
      table, th, td {
          border: 1px solid black;
          border-collapse: collapse;
      }

      form {
          padding: 12px 20px;
          width: 20%;
          margin: 8px 0;
          display: flex;
          flex-direction: column;
          border: 1px solid #ccc;
          border-radius: 4px;
          box-sizing: border-box;
      }

      .error {
          background-color: indianred;
          border: 1px solid red;
      }

      .error {
          background-color: limegreen;
          border: 1px solid green;
      }
  </style>
</head>
<body>

<a href="/users">Powrot</a>

<#if errorMessage??>
  <p class="error">Zmiana nie powiodla sie: ${errorMessage}</p>
</#if>

<#if success??>
  <p class="success">Zmiana powiodla sie.</p>
</#if>

<form method="post" name="changePassword" action="/admins/password">
  <label for="previousPassword"> Poprzednie haslo </label> <input type="password" id="previousPassword"
                                                                  name="previousPassword"/>
  <label for="newPassword1"> Nowe haslo </label> <input type="password" id="newPassword1" name="newPassword1"/>
  <label for="newPassword2"> Powtorz haslo </label> <input type="password" id="newPassword2" name="newPassword2"/>
  <label for="change-btn">Zmien</label> <input id="change-btn" type="submit"/>
</form>

</body>
</html>
