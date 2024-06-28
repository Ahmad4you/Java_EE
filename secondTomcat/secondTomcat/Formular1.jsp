<%@ page import = " java.util.*, bp.Formular1Receiver" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	Formular1Receiver fr = Formular1Receiver.getInstance();
	fr.getData(request);
%>
<html>
<body>

<form action="Formular1.jsp" method="post">
  <input type="hidden" name="hidden" value="hid">
  <table border=0 cellspacing=3 cellpadding=3>
    <tr bgcolor='#EBEEEE'><th colspan='2'>
        <big>Formular</big><br>
        Bitte Eingaben ändern und Submit betätigen</th></tr>
    <tr bgcolor="#EBEEEE"><td>SelectDropDown</td>
      <td>
        <select name="SelectDropDown" size=1>
          <option value="1">Opt. 1</option>
          <option value="2" selected>Opt. 2</option>
          <option value="3">Opt. 3</option>
          <option value="4">Opt. 4</option>
        </select>
      </td></tr>
    <tr bgcolor="#EBEEEE"><td>SelectMultiple</td>
      <td>
        <select name="SelectMultiple" size=3 multiple>
          <option value="1">Opt. 1</option>
          <option value="2">Opt. 2</option>
          <option value="3" selected>Opt. 3</option>
          <option value="4" selected>Opt. 4</option>
        </select>
      </td></tr>
    <tr bgcolor="#EBEEEE"><td>Textarea</td>
      <td>
        <textarea name="Textarea" cols=20 rows=3>Text ...</textarea>
      </td></tr>
    <tr bgcolor="#EBEEEE"><td>Textfeld</td>
      <td>
        <input type="text" name="Textfeld" value=<%=fr.getText() %> size=20 maxlength=50>
      </td></tr>
    <tr bgcolor="#EBEEEE"><td>Passwort</td>
      <td>
        <input type="password" name="Passwort" value="xx" size=20 maxlength=10>
      </td></tr>
    <tr bgcolor="#EBEEEE"><td>Checkboxen cb1...cb3</td>
      <td>
        <input type="checkbox" name="cb1"  <%= fr.getCheckStatus(1) %>>
        <input type="checkbox" name="cb2" <%= fr.getCheckStatus(2) %>>
        <input type="checkbox" name="cb3"<%= fr.getCheckStatus(3) %>>
      </td></tr>
    <tr bgcolor="#EBEEEE"><td>Radiobuttons ra</td>
      <td>
        <input type="radio" name="ra" value="1">
        <input type="radio" name="ra" value="2" checked>
        <input type="radio" name="ra" value="3">
      </td></tr>
    <tr bgcolor="#EBEEEE"><td>Submit</td>
      <td>
        <button type="submit" name="Submit" value="SubmitImg">
          <img src="Lager.jpg" alt="Submit">
        </button>
        <input type="submit" name="Submit" value="Submit1">
        <input type="submit" name="Submit" value="Submit2">
      </td></tr>
  </table>
</form>

<a name="Scroll"></a>

</body>
</html>
