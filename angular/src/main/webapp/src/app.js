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
                templateUrl: 'src/list.html',
                controller: 'CategoryCtrl'
            })
            .when('/create/category', {
                templateUrl: 'src/createCategory.html',
                controller: 'CreateCategoryCtrl'
            })
            .when('/create/category/:categoryId', {
                templateUrl: 'src/createCategory.html',
                controller: 'CreateCategoryCtrl'
            })
            .when('/createTest/:categoryId', {
                templateUrl: 'src/createTest.html',
                controller: 'CreateTestCtrl'
            })
            .when('/test/:testId/update', {
                templateUrl: 'src/createTest.html',
                controller: 'UpdateTestCtrl'
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