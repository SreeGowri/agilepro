$.application.controller('memberController', ["$scope", "crudController", "actionHelper", "utils", 
                                        function($scope, crudController, actionHelper, utils){
	
	var readEmpcallBack = function(readResponse, respConfig){

		$scope.employees = readResponse.model;
		
		var index;
		
		var fileId;
		
		for(index in $scope.employees)
		{
			fileId = $scope.employees[index].photo.fileId;
			
			$scope.employees[index].photoUrl = actionHelper.actionUrl("files.fetch", {"id": fileId});
		}
		
		$scope.$apply();
	};
		
	
	$scope.searchEmployee = function(employeeName){
		
		if(employeeName)
		{
			console.log(employeeName);
			actionHelper.invokeAction("employee.readAll", null, {"employeeName" : employeeName}, readEmpcallBack);
			return;
		}
		
		actionHelper.invokeAction("employee.readAll", null, null, readEmpcallBack);
	};
	
	var teamIds = [];
	
	$scope.members = [];
	
	$scope.admins = [];
	
	$scope.manager = {};
	
	var empObj;
	
	
	$scope.dragEmployees = function(event){

		console.log("dragEmployees invoked");
		empObj = JSON.parse(event.target.id);
		
		//event.dataTransfer.setData("text", event.target.id);
	};
	
	 $scope.allowDrop = function(event) {
		 
	    event.preventDefault();
	}
	
	 $scope.dropMembers = function(event){
		 
		 	console.log("drop");
		    event.preventDefault();
		    
		    var index = teamIds.indexOf(empObj.id);
		    console.log("index " + index);
			
			if(index == -1)
			{
				teamIds.push(empObj.id);
				$scope.members.push(empObj);
				
				saveProjectMembers(empObj);
			}
			else if(index >= 0)
			{
				utils.alert("He or she is already a member of the team please remove it from one of the group");
				return;
			}
			
		    /*var data = event.dataTransfer.getData("text");
		    event.target.appendChild(document.getElementById(data));*/
			
			$scope.$apply();
		}
	 
	 $scope.dropAdmins = function(event){
		 
		 	console.log("drop");
		    event.preventDefault();
		    
		    var index = teamIds.indexOf(empObj.id);
		    console.log("index " + index);
			
			if(index == -1)
			{
				teamIds.push(empObj.id);
				$scope.admins.push(empObj);
				
				saveProjectMembers(empObj);
			}
			else if(index >= 0)
			{
				utils.alert("He or she is already a member of the team please remove it from one of the group");
				return;
			}
			
			$scope.$apply();
	 };
	
	 $scope.dropManager = function(event){
		 	
		 	console.log("drop");
		    event.preventDefault();
		    
		    var index = teamIds.indexOf(empObj.id);
		    console.log("index " + index);
			
			if(index == -1)
			{
				if($scope.manager.name)
				{
					index = teamIds.indexOf($scope.manager.id);
					
					teamIds.splice(index, 1);
					
					deleteProjectMembers($scope.manager);
				}
				
				teamIds.push(empObj.id);
				$scope.manager = empObj;
				
				saveProjectMembers(empObj);
			}
			else if(index >= 0)
			{
				utils.alert("He or she is already a member of the team please remove it from one of the group");
				return;
			}
			
			$scope.$apply();
	 };
	 
	$scope.removeMembers = function(data){
		
		var index = teamIds.indexOf(data.id);
		
		if(index >= 0)
		{
			teamIds.splice(index, 1);
			
			index = $scope.members.indexOf(data);
			
			$scope.members.splice(index, 1);
			
			deleteProjectMembers(data);
		}
	};
	
	$scope.removeAdmins = function(data){
		
		var index = teamIds.indexOf(data.id);
		
		if(index >= 0)
		{
			teamIds.splice(index, 1);
			
			index = $scope.admins.indexOf(data);
			
			$scope.admins.splice(index, 1);
		}
	};
	
	
	$scope.removeManager = function(data){
		
		var index = teamIds.indexOf(data.id);
		
		if(index >= 0)
		{
			teamIds.splice(index, 1);
			
			$scope.manager = {};
		}
	};
	
	var callBack = function(readResponse, respConfig){

		
	};
	
	saveProjectMembers = function(empObj){
		
		model = {"employeeId": empObj.id, "projectId" : 1, "userRole" : "PROJECT_MEMBER_EDIT"};
		
		actionHelper.invokeAction("projectMember.save", model, null, callBack);
		
	};
	
	deleteProjectMembers = function(empObj){
		
		actionHelper.invokeAction("projectMember.delete", null, {"id" : empObj.id}, callBack);
		
	};
		
}]);
