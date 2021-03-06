
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%> 
<!DOCTYPE html>
    <html>
   <head>
      <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
      <title>Create Order</title>
<!--       <script type="text/javascript" src="pm-createorder-script.js"></script>
     <link rel="stylesheet" href="pm-createorder-style.css" /> -->
     <style>
      #creat-order-form{
    width:80%;
    border: 
}

.form-input-label{
    margin-bottom: 10px;
}

.form-input-fields{
    width:auto;
    margin-bottom: 10px;
    border-radius: 10px;
    border: 1px solid grey;
    padding:4px;
}


.form-input-fields input, select{
    border:none;
    background-color: transparent;
}

.form-input-fields select:onfocus{
    border: none;
}

input[type="number"]:disabled {
    background-color:grey;
    border-radius: 10px;
    opacity: 0.3;
}

textarea:focus, select:focus, input:focus{
    outline: none;
}



$dia: 6em;
$outcolor: #fff;
$bdrwidth: 10px;
$time: 0.15s;
</style>
      
      
       <script>   
function changetextbox(obj)
{  
      var limit = document.getElementById("limit");
     var stop = document.getElementById("stop");
    	
        stop.disabled =(obj.value == "limit" || obj.value == "market");
        limit.disabled =(obj.value == "stop" || obj.value == "market");
    
}
     </script>
   </head>

<body>
	 <!-- Navigation Bar -->
   <nav class="navbar navbar-default">
      <div class="container-fluid">
         <div class="navbar-header">
            <a class="navbar-brand" href="./PMHome.jsp">Portfolio Manager</a>
         </div>
         <ul class="nav navbar-nav">
             <li><a href="./PMHome.jsp">Home</a></li>
            <li class="active"><a href="./CreateTrade.jsp">Create Trade</a></li>
            <li ><a href="./OrderBlotter1.jsp">Order Blotter</a></li>
            <li ><a href="./PendingOrder.jsp">Pending Orders</a></li>
            <li><a href="./PMHistory.jsp">History</a></li>
         </ul>
      </div>
    </nav>
    
    <div id="creat-order-form" class="container well">
	<h2 id="eqorder">Create Equity Order</h2>

	
						<form action="CreateOrder">           
             <div class="well" >  
            

                <div class="row">
                <div id="" class="form-input-label col col-sm-4"> Symbol: </div>
               <div class="form-input-fields col col-sm-8"><input type="text" id="symbol" placeholder="Enter Stock Name" required/></div>
                   </div>
                 
                <div class="row">
                <div id="" class="form-input-label col col-sm-4"> Side: </div>
               <div class="form-input-fields col col-sm-8"><select id="side"  required>
                  <option> BUY</option>
                  <option> SELL</option>
               </select>
              </div>
                    </div>
                <div class="row">
                <div id="" class="form-input-label col col-sm-4"> Order Type: </div>
               <div class="form-input-fields col col-sm-8"><select id="ordertype" onChange="changetextbox(this);" id="order-type" name="order-type" required>
                  <option value="market"> Market</option>
                  <option value="limit"> Limit</option>
                  <option value="stoplimit"> Stop-Limit</option>
                  <option value="stop"> Stop </option>
               </select>
                </div>   
                   </div>
                 
                   <div class="row">
                <div id="" class="form-input-label col col-sm-4"> Order Qualifier: </div>
               <div class="form-input-fields col col-sm-8"><select id="orderqual" name="qualifier" required>
                  <option> Day Order</option>
                  <option> GTC</option>
               </select>
                   </div>
                   </div>
						 <div class="row">
                <div id="" class="form-input-label col col-sm-4"> Trader: </div>
               <div class="form-input-fields col col-sm-8">
                   <input type="text" name="traderId" id="trader"  required>
                 
          
                   </div>
                   </div>
				
				
                 <div class="row">
                <div id="" class="form-input-label col col-sm-4"> Account Type: </div>
               <div class="form-input-fields col col-sm-8"><select id="acctype" name="accountType" style="" required>
                  <option> Cash</option>
                  <option> Margin</option>
               </select>
                   </div>
                  </div>
							   <div class="row">
                <div id="" class="form-input-label col col-sm-4"> Portfolio: </div>
               <div class="form-input-fields col col-sm-8">
                   <input type="number" name="portfolioId" id="portfolio" required>
      
                   </div>
                   </div>
				
						    <div class="row">
                <div id="" class="form-input-label col col-sm-4"> Quantity: </div>
               <div class="form-input-fields col col-sm-8"><input type="number" min="0" id="quantity" required/></div>
                    </div>   
               
                <div class="row">
                <div id="" class="form-input-label col col-sm-4"> Stop Price: </div>
               <div class="form-input-fields col col-sm-8"><input type="number" id="stop" min="0" disabled required/></div>
                  </div>
                <div class="row">
                <div id="" class="form-input-label col col-sm-4"> Limit Price: </div>
               <div class="form-input-fields col col-sm-8"><input type="number" id="limit" min="0"  disabled required/></div>
                   </div>
                 </div>
							 <input type="submit" class="btn btn-default"  value="CREATE"/>
            </form>
           
         </div>
           
   </body>
</html>
