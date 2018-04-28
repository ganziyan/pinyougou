/*app.controller("indexController", function ($scope, $controller, loginService) {
    //读取当前联系人
    $scope.showLoginName = function () {
        loginService.loginName().success(function (response) {
            $scope.loginName=response.loginName;
        })
    };
});*/


app.controller("indexController",function ($scope, $controller, loginService) {
    $scope.showLoginName=function () {
        loginService.loginName().success(function (response) {
            $scope.loginName=response.loginName;
        })
    }
})