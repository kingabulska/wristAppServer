<!DOCTYPE html>
<html lang="pl">
<head>
  <meta charset="UTF-8">
  <title>Administratorzy</title>
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
  </style>
</head>
<body>
<div>
  <#if errorMessage??>
    <p class="error">Tworzenie nie powiodlo sie: ${errorMessage}</p>
  </#if>
  <a href="/">Powrot</a>
  <table style="width:40%">
    <tr>
      <th>Imie</th>
      <th>Nazwisko</th>
      <th>Login</th>
    </tr>
      <#list admins as admin>
        <tr>
          <td>${admin.name!}</td>
          <td>${admin.surname!}</td>
          <td>${admin.username}</td>
        </tr>
      </#list>
  </table>

  <form method="post" name="admin" action="/admins" style="">
    <label for="name" title="Imie">Imie</label><input id="name" name="name"/>
    <label for="surname" title="Nazwisko">Nazwisko</label><input id="surname" name="surname"/>
    <label for="username" title="Login">Login</label><input id="username" name="username"/>
    <label for="password" title="Haslo">Haslo</label><input id="password" type="password" name="password"/>
    <input type="submit"/>
  </form>
</div>

</body>
</html>
