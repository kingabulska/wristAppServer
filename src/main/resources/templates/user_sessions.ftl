<!DOCTYPE html>
<html lang="pl">
<head>
  <meta charset="UTF-8">
  <title>Pacjent</title>
</head>
<body>

<a href="/users">Powrot do listy pacjentow</a>

<#if user??>
  <form action="./download" method="get">
    <p>Pacjent: ${user.description!}</p>
    <table style="width:80%">
      <tr>
        <th>Id</th>
        <th>Start sesji</th>
      </tr>
        <#list sessions as session>
          <tr>
            <td>
              <input type="checkbox" name="sessions" id="${session.id}" value="${session.id}">
              <label for="${session.id}">
                <a href="/users/${user.id}/sessions/${session.id}">${session.id}</a>
              </label>
            </td>
            <td>${session.sessionTimestamp}</td>
          </tr>
        </#list>
    </table>
    <input type="checkbox" onclick="toggle(this)" id="select-all-checkbox">
    <label for="select-all-checkbox"> Zaznacz wszystkie</label>
    <button type="submit">Pobierz zaznaczone</button>
  </form>
  <script>
    function toggle(source) {
      checkboxes = document.getElementsByName('sessions');
      for(var i=0, n=checkboxes.length;i<n;i++) {
        checkboxes[i].checked = source.checked;
      }
    }
  </script>
<#else>
  <p>Pacjent nie istnieje</p>
</#if>


</body>
</html>
