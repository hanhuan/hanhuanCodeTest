<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>HanHuan Code Test</title>
	<script src="http://code.jquery.com/jquery-2.0.0.min.js"></script>

	<script type="text/javascript">
		$(function(){
			$("#submit").bind("click",function(){
				findRecipesAjax($("#codeTestTextAreaForFridge").val(),$("#codeTestTextAreaForRecipe").val());
			});
		
		});
		
		function findRecipesAjax(fridge,recipe){
			$.ajax({
				url : "${pageContext.request.contextPath}/findRecipes?fridge="+fridge+"&recipe="+recipe,
				dataType : "jsonp",
				success : function(data) {
					  alert(data.result);
				},
				error:function(){
					alert("error");
				}
			});
		}
		
	</script>
</head>
<body>
<h1> Code Test Of hanhuan@cn.ibm.com</h1>
<hr/>
<div>
	<div>
		Item : the name of the ingredient ?e.g. egg)                 <br/>
		Amount :the amount										     <br/>
		Unit : the unit of measure, values;							 <br/>
			&nbsp&nbsp of (for individual items; eggs, bananas etc)	 <br/>
			&nbsp&nbsp grams										 <br/>
			&nbsp&nbsp ml (milliliters)							     <br/>
			&nbsp&nbsp slices							             <br/>
		Use-By (date) : the use by date of the ingredient (dd/mm/yy) <br/>
	</div>

	<textarea id="codeTestTextAreaForFridge" cols="100" rows="20">
	[
	    {
	        "name": "bread",
	        "amount": 10,
	        "unit": "slices",
	        "expireDate": "25/12/2015"
	    },
	    {
	        "name": "cheese",
	        "amount": 10,
	        "unit": "slices",
	        "expireDate": "25/12/2014"
	    },
	    {
	        "name": "butter",
	        "amount": 250,
	        "unit": "grams",
	        "expireDate": "25/12/2015"
	    },
	    {
	        "name": "peanut butter",
	        "amount": 250,
	        "unit": "grams",
	        "expireDate": "02/12/2015"
	    },
	    {
	        "name": "mixed salad",
	        "amount": 150,
	        "unit": "grams",
	        "expireDate": "26/12/2015"
	    }
	]
	</textarea>
</div>
<div>
	<div>Recipes:</div>
	<form id="codeTestForm" action="" method="post" >
		<textarea id="codeTestTextAreaForRecipe" cols="100" rows="20">
		[
		    {
		        "name": "grilled cheese on toast",
		        "ingredients": [
		            {
		                "item": "bread",
		                "amount": "2",
		                "unit": "slices"
		            },
		            {
		                "item": "cheese",
		                "amount": "2",
		                "unit": "slices"
		            }
		        ]
		    },
		    {
		        "name": "salad sandwich",
		        "ingredients": [
		            {
		                "item": "bread",
		                "amount": "2",
		                "unit": "slices"
		            },
		            {
		                "item": "mixed salad",
		                "amount": "100",
		                "unit": "grams"
		            }
		        ]
		    }
		]
		</textarea>
		<input id="submit" type="button" value="submit"/>
	</form>
</div>
<hr/>

</body>
</html>
