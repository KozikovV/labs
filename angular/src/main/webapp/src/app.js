var myApp = angular.module('myApp', ['ngRoute',
    'controllers', 'adminkaControllers', 'service']);

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
            .when('/category/:categoryId/update', {
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
            //TODO | need to separate adminka to it's directory
            .when('/adminka', {
                templateUrl: '/src/adminka/main.html',
                controller: 'AdminkaMainCtrl'
            })
            .when('/adminka/images', {
                templateUrl: '/src/adminka/images.html',
                controller: 'AdminkaImagesCtrl'
            })
            .when('/adminka/users', {
                templateUrl: '/src/adminka/users.html',
                controller: 'AdminkaUsersCtrl'
            })
            .otherwise({
                redirectTo: '/list'
            });

    }]);

myApp.run(function($rootScope, Config){
    $rootScope.config = Config.load();
    console.log($rootScope.config);
});

myApp.directive('imageBrowser', function(){
    return {
        templateUrl: '/src/imageBrowser.html',
        scope: {
            images: '=images'
        },
            controller: function ($scope, $element, $attrs, $transclude, Config) {
                $scope.config = Config.load();
        }
    }
})

myApp.service('taskService', function () {
    var _tasks = [];

    this.setTasks = function (tasks) {
        _tasks = tasks;
    }

    this.getTasks = function () {
        return _tasks;
    }
});