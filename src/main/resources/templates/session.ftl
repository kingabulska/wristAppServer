<!DOCTYPE html>
<html lang="pl">
<head>
  <#setting number_format="computer">
  <meta charset="UTF-8">
  <meta http-equiv="Content-Language" content="pl">
  <title>Examine page</title>

  <style>
      canvas {
        border: 1px solid black;
      }

      table, th, td {
          border: 1px solid black;
          border-collapse: collapse;
      }

      .ellipse-data {
          border: 1px solid black;
          border-collapse: collapse;
      }

      .ellipses-tables {
          border: 1px solid black;
          border-collapse: collapse;
          display: flex;
          flex-direction: row;
          width: fit-content;
      }

      .tables {
          display: flex;
          flex-wrap: nowrap;
          overflow-x: auto;
      }
  </style>

</head>
<body>

<a href="/users">Powrot do listy pacjentow</a><br>

<#if user?? && session??>

  <a href="/users/${user.id}/sessions">Powrot do pacjenta ${user.id}</a>
  <div class="session">
    <p>Id sesji: ${session.id}</p>
    <p>Rozpoczeto: ${session.sessionTimestamp}</p>
    <p>Wysokosc urzadzenia: ${session.deviceHeight}px</p>
    <p>Szerokosc urzadzenia: ${session.deviceWidth}px</p>
  </div>

  <div class="tables">

      <#if leftExamine1??>
        <div class="hand">
          <p>Reka lewa 1</p>
          <div class="ellipses-tables">

              <#if leftExamine1Ellipse1??>
                <div class="left-1-ellipse-1 ellipse-data">
                  <p>Elipsa 1</p>
                  <p>S: ${leftExamine1Ellipse1.start}</p>
                  <p>K: ${leftExamine1Ellipse1.end}</p>
                  <p>Cos sr: <#if leftExamine1Ellipse1.cosArrayMean??> ${leftExamine1Ellipse1.cosArrayMean} <#else> - </#if> </p>
                  <table>
                    <tr>
                      <th>t</th>
                      <th>x</th>
                      <th>y</th>
                      <th>cos</th>
                    </tr>
                      <#list leftExamine1Ellipse1.drawing as draw>
                        <tr>
                          <td>${draw.timestamp}</td>
                          <td>${draw.x}</td>
                          <td>${draw.y}</td>
                          <td> <#if draw.cos??> ${draw.cos} <#else> - </#if> </td>
                        </tr>
                      </#list>
                  </table>
                </div>
              </#if>

              <#if leftExamine1Ellipse2??>
                <div class="left-1-ellipse-2 ellipse-data">
                  <p>Elipsa 2</p>
                  <p>S: ${leftExamine1Ellipse2.start}</p>
                  <p>K: ${leftExamine1Ellipse2.end}</p>
                  <p>Cos sr: <#if leftExamine1Ellipse2.cosArrayMean??> ${leftExamine1Ellipse2.cosArrayMean} <#else> - </#if> </p>
                  <table>
                    <tr>
                      <th>t</th>
                      <th>x</th>
                      <th>y</th>
                      <th>cos</th>
                    </tr>
                      <#list leftExamine1Ellipse2.drawing as draw>
                        <tr>
                          <td>${draw.timestamp}</td>
                          <td>${draw.x}</td>
                          <td>${draw.y}</td>
                          <td> <#if draw.cos??> ${draw.cos} <#else> - </#if> </td>
                        </tr>
                      </#list>
                  </table>
                </div>
              </#if>

              <#if leftExamine1Ellipse3??>
                <div class="left-1-ellipse-3 ellipse-data">
                  <p>Elipsa 3</p>
                  <p>S: ${leftExamine1Ellipse3.start}</p>
                  <p>K: ${leftExamine1Ellipse3.end}</p>
                  <p>Cos sr: <#if leftExamine1Ellipse3.cosArrayMean??> ${leftExamine1Ellipse3.cosArrayMean} <#else> - </#if> </p>
                  <table>
                    <tr>
                      <th>t</th>
                      <th>x</th>
                      <th>y</th>
                      <th>cos</th>
                    </tr>
                      <#list leftExamine1Ellipse3.drawing as draw>
                        <tr>
                          <td>${draw.timestamp}</td>
                          <td>${draw.x}</td>
                          <td>${draw.y}</td>
                          <td> <#if draw.cos??> ${draw.cos} <#else> - </#if> </td>
                        </tr>
                      </#list>
                  </table>
                </div>
              </#if>

          </div>
        </div>
      </#if>

      <#if leftExamine2??>
        <div class="hand">
          <p>Reka lewa 2</p>
          <div class="ellipses-tables">

              <#if leftExamine2Ellipse1??>
                <div class="left-1-ellipse-1 ellipse-data">
                  <p>Elipsa 1</p>
                  <p>S: ${leftExamine2Ellipse1.start}</p>
                  <p>K: ${leftExamine2Ellipse1.end}</p>
                  <p>Cos sr: <#if leftExamine2Ellipse1.cosArrayMean??> ${leftExamine2Ellipse1.cosArrayMean} <#else> - </#if> </p>
                  <table>
                    <tr>
                      <th>t</th>
                      <th>x</th>
                      <th>y</th>
                      <th>cos</th>
                    </tr>
                      <#list leftExamine2Ellipse1.drawing as draw>
                        <tr>
                          <td>${draw.timestamp}</td>
                          <td>${draw.x}</td>
                          <td>${draw.y}</td>
                          <td> <#if draw.cos??> ${draw.cos} <#else> - </#if> </td>
                        </tr>
                      </#list>
                  </table>
                </div>
              </#if>

              <#if leftExamine2Ellipse2??>
                <div class="left-1-ellipse-2 ellipse-data">
                  <p>Elipsa 2</p>
                  <p>S: ${leftExamine2Ellipse2.start}</p>
                  <p>K: ${leftExamine2Ellipse2.end}</p>
                  <p>Cos sr: <#if leftExamine2Ellipse2.cosArrayMean??> ${leftExamine2Ellipse2.cosArrayMean} <#else> - </#if> </p>
                  <table>
                    <tr>
                      <th>t</th>
                      <th>x</th>
                      <th>y</th>
                      <th>cos</th>
                    </tr>
                      <#list leftExamine2Ellipse2.drawing as draw>
                        <tr>
                          <td>${draw.timestamp}</td>
                          <td>${draw.x}</td>
                          <td>${draw.y}</td>
                          <td> <#if draw.cos??> ${draw.cos} <#else> - </#if> </td>
                        </tr>
                      </#list>
                  </table>
                </div>
              </#if>

              <#if leftExamine2Ellipse3??>
                <div class="left-1-ellipse-3 ellipse-data">
                  <p>Elipsa 3</p>
                  <p>S: ${leftExamine2Ellipse3.start}</p>
                  <p>K: ${leftExamine2Ellipse3.end}</p>
                  <p>Cos sr: <#if leftExamine2Ellipse3.cosArrayMean??> ${leftExamine2Ellipse3.cosArrayMean} <#else> - </#if> </p>
                  <table>
                    <tr>
                      <th>t</th>
                      <th>x</th>
                      <th>y</th>
                      <th>cos</th>
                    </tr>
                      <#list leftExamine2Ellipse3.drawing as draw>
                        <tr>
                          <td>${draw.timestamp}</td>
                          <td>${draw.x}</td>
                          <td>${draw.y}</td>
                          <td> <#if draw.cos??> ${draw.cos} <#else> - </#if> </td>
                        </tr>
                      </#list>
                  </table>
                </div>
              </#if>

          </div>
        </div>
      </#if>


      <#if rightExamine1??>
        <div class="hand">
          <p>Reka prawa 1</p>
          <div class="ellipses-tables">

              <#if rightExamine1Ellipse1??>
                <div class="left-1-ellipse-1 ellipse-data">
                  <p>Elipsa 1</p>
                  <p>S: ${rightExamine1Ellipse1.start}</p>
                  <p>K: ${rightExamine1Ellipse1.end}</p>
                  <p>Cos sr: <#if rightExamine1Ellipse1.cosArrayMean??> ${rightExamine1Ellipse1.cosArrayMean} <#else> - </#if> </p>
                  <table>
                    <tr>
                      <th>t</th>
                      <th>x</th>
                      <th>y</th>
                      <th>cos</th>
                    </tr>
                      <#list rightExamine1Ellipse1.drawing as draw>
                        <tr>
                          <td>${draw.timestamp}</td>
                          <td>${draw.x}</td>
                          <td>${draw.y}</td>
                          <td> <#if draw.cos??> ${draw.cos} <#else> - </#if> </td>
                        </tr>
                      </#list>
                  </table>
                </div>
              </#if>

              <#if rightExamine1Ellipse2??>
                <div class="left-1-ellipse-2 ellipse-data">
                  <p>Elipsa 2</p>
                  <p>S: ${rightExamine1Ellipse2.start}</p>
                  <p>K: ${rightExamine1Ellipse2.end}</p>
                  <p>Cos sr: <#if rightExamine1Ellipse2.cosArrayMean??> ${rightExamine1Ellipse2.cosArrayMean} <#else> - </#if> </p>
                  <table>
                    <tr>
                      <th>t</th>
                      <th>x</th>
                      <th>y</th>
                      <th>cos</th>
                    </tr>
                      <#list rightExamine1Ellipse2.drawing as draw>
                        <tr>
                          <td>${draw.timestamp}</td>
                          <td>${draw.x}</td>
                          <td>${draw.y}</td>
                          <td> <#if draw.cos??> ${draw.cos} <#else> - </#if> </td>
                        </tr>
                      </#list>
                  </table>
                </div>
              </#if>

              <#if rightExamine1Ellipse3??>
                <div class="left-1-ellipse-3 ellipse-data">
                  <p>Elipsa 3</p>
                  <p>S: ${rightExamine1Ellipse3.start}</p>
                  <p>K: ${rightExamine1Ellipse3.end}</p>
                  <p>Cos sr: <#if rightExamine1Ellipse3.cosArrayMean??> ${rightExamine1Ellipse3.cosArrayMean} <#else> - </#if> </p>
                  <table>
                    <tr>
                      <th>t</th>
                      <th>x</th>
                      <th>y</th>
                      <th>cos</th>
                    </tr>
                      <#list rightExamine1Ellipse3.drawing as draw>
                        <tr>
                          <td>${draw.timestamp}</td>
                          <td>${draw.x}</td>
                          <td>${draw.y}</td>
                          <td> <#if draw.cos??> ${draw.cos} <#else> - </#if> </td>
                        </tr>
                      </#list>
                  </table>
                </div>
              </#if>

          </div>
        </div>
      </#if>

      <#if rightExamine2??>
        <div class="hand">
          <p>Reka prawa 2</p>
          <div class="ellipses-tables">

              <#if rightExamine2Ellipse1??>
                <div class="left-1-ellipse-1 ellipse-data">
                  <p>Elipsa 1</p>
                  <p>S: ${rightExamine2Ellipse1.start}</p>
                  <p>K: ${rightExamine2Ellipse1.end}</p>
                  <p>Cos sr: <#if rightExamine2Ellipse1.cosArrayMean??> ${rightExamine2Ellipse1.cosArrayMean} <#else> - </#if> </p>
                  <table>
                    <tr>
                      <th>t</th>
                      <th>x</th>
                      <th>y</th>
                      <th>cos</th>
                    </tr>
                      <#list rightExamine2Ellipse1.drawing as draw>
                        <tr>
                          <td>${draw.timestamp}</td>
                          <td>${draw.x}</td>
                          <td>${draw.y}</td>
                          <td> <#if draw.cos??> ${draw.cos} <#else> - </#if> </td>
                        </tr>
                      </#list>
                  </table>
                </div>
              </#if>

              <#if rightExamine2Ellipse2??>
                <div class="left-1-ellipse-2 ellipse-data">
                  <p>Elipsa 2</p>
                  <p>S: ${rightExamine2Ellipse2.start}</p>
                  <p>K: ${rightExamine2Ellipse2.end}</p>
                  <p>Cos sr: <#if rightExamine2Ellipse2.cosArrayMean??> ${rightExamine2Ellipse2.cosArrayMean} <#else> - </#if> </p>
                  <table>
                    <tr>
                      <th>t</th>
                      <th>x</th>
                      <th>y</th>
                      <th>cos</th>
                    </tr>
                      <#list rightExamine2Ellipse2.drawing as draw>
                        <tr>
                          <td>${draw.timestamp}</td>
                          <td>${draw.x}</td>
                          <td>${draw.y}</td>
                          <td> <#if draw.cos??> ${draw.cos} <#else> - </#if> </td>
                        </tr>
                      </#list>
                  </table>
                </div>
              </#if>

              <#if rightExamine2Ellipse3??>
                <div class="left-1-ellipse-3 ellipse-data">
                  <p>Elipsa 3</p>
                  <p>S: ${rightExamine2Ellipse3.start}</p>
                  <p>K: ${rightExamine2Ellipse3.end}</p>
                  <p>Cos sr: <#if rightExamine2Ellipse3.cosArrayMean??> ${rightExamine2Ellipse3.cosArrayMean} <#else> - </#if> </p>
                  <table>
                    <tr>
                      <th>t</th>
                      <th>x</th>
                      <th>y</th>
                      <th>cos</th>
                    </tr>
                      <#list rightExamine2Ellipse3.drawing as draw>
                        <tr>
                          <td>${draw.timestamp}</td>
                          <td>${draw.x}</td>
                          <td>${draw.y}</td>
                          <td> <#if draw.cos??> ${draw.cos} <#else> - </#if> </td>
                        </tr>
                      </#list>
                  </table>
                </div>
              </#if>

          </div>
        </div>
      </#if>
  </div>

  <div class="tables">
    <#list renders as rend>
        <div>
            <p>${rend.hand!} ${rend.examineNumber!} Elipsa ${rend.ellipseNumber!}</p>
            <canvas id="${rend.id}" width="${rend.canvasWidth}" height="${rend.canvasHeight}"></canvas>
            <script>
              var canvas = document.getElementById('${rend.id}');
              var ctx = canvas.getContext('2d');

              ctx.beginPath();
              <#list rend.referenceEllipse as pt>
                ctx.lineTo(${pt[0]}, ${pt[1]});
              </#list>
              ctx.stroke();


              ctx.beginPath();
              <#list rend.recordedPoints as pt>
                ctx.lineTo(${pt[0]}, ${pt[1]});
                ctx.strokeStyle = '#ff0000';
              </#list>
              ctx.stroke();


            </script>
        </div>
    </#list>
  </div>

<#else>
  <p>Pacjent lub sesja nie istnieje</p>
</#if>


</body>
</html>
