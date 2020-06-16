<html>
<head>
    <title>Api Command option</title>
</head>

<body>
<h2>Select your API command key</h2>
<br/><br/>

<form action="processor" method="GET" target="_self">
    <a>Select commands</a>
    <select name="cmd">
        <option value="1">The list of most active authors according to a set threshold</option>
        <option value="2">The author with the highest comment count.</option>
        <option value="3">The list of the authors sorted by when their record was created according to a set threshold.</option>
      </select>

    <br/>
    <br/>
    <a>Enter page number</a>
    <input type="number" name="pid"/>

    <br/>
    <br/>
    <input type="submit" value="Submit">
</form>

</body>
</html>