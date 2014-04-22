var myApp = angular.module('myApp', ['ngRoute', 'controllers']);

myApp.config(['$routeProvider',
    function ($routeProvider) {
        $routeProvider
            .when('/list', {
                templateUrl: 'src/list.html',
                controller: 'ListCtrl'
            })
            .when('/test/:testId', {
                templateUrl: 'src/test.html',
                controller: 'TestCtrl'
            })
            .when('/category/:categoryId', {
                templateUrl: 'src/category.html',
                controller: 'CategoryCtrl'
            })
            .when('/createTest/:categoryId', {
                templateUrl: 'src/createTest.html',
                controller: 'CreateTestCtrl'
            })
            .otherwise({
                redirectTo: '/list'
            });

    }]);

myApp.service('taskService', function () {
    var _tasks = [];

    this.setTasks = function (tasks) {
        _tasks = tasks;
    }

    this.getTasks = function () {
        return _tasks;
    }
});