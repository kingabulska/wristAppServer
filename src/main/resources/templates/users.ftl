<!DOCTYPE html>
<html lang="pl">
<head>
  <meta charset="UTF-8">
  <title>Pacjentci</title>
  <style>
      table, th, td {
          border: 1px solid black;
          border-collapse: collapse;
      }
  </style>
</head>
<body>

<a href="/users/create">Stworz konto pacjenta</a> <br>
<#if isSuperAdmin>
  <a href="/admins">Zarzadzaj administratorami</a> <br>
</#if>
<a href="/admins/password">Zmien haslo</a> <br>
<table style="width:60%">
  <tr>
    <th>Id</th>
    <th>Opis</th>
    <th>Kod</th>
    <th>Utworzony dnia</th>
  </tr>
    <#list users as user>
      <tr>
        <td> <a href="/users/${user.id}/sessions">${user.id}</a> </td>
        <td>${user.description!}</td>
        <td>${user.code}</td>
        <td>${user.createdAt}</td>
      </tr>
    </#list>
</table>

</body>
</html>
