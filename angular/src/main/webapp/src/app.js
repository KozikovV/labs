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

myApp.run(function ($rootScope, Config) {
    $rootScope.config = Config.load();
    console.log($rootScope.config);
});

myApp.directive('imageBrowser', function () {
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

myApp.directive('validFile', function () {
    return {
        require: 'ngModel',
        link: function (scope, el, attrs, ngModel) {
            el.bind('change', function () {
                scope.$apply(function () {
                    ngModel.$setViewValue(el.val());
                    ngModel.$render();
                });
            });
            el.bind('click', function () {
                scope.$apply(function () {
                    ngModel.$setViewValue();
                    ngModel.$render();
                });
            });
        }
    }
});

myApp.service('globalMessageService', [function () {
//    /**
//     * @type {{key:{
//     *     text: ' ',
//     *     type: 'danger'
//     * }}}
//     */
        var messages = [];
        /**
         * @type function(messages)
         */
        var updateListener;
        return {
            setUpdateListener: function (listener) {
                updateListener = listener;
            },
            addDanger: function (message) {
                messages.push({
                    text: message,
                    type: 'danger'
                });
                updateListener(messages);
//                setTimeout(function () {
//                    delete messages[key];
//                    updateListener(messages);
//                }, 4000);
            },
            getMessages: function () {
                return messages;
            },
            removeMessage: function(message){
                messages.splice(messages.indexOf(message), 1);
                updateListener(messages);
            }
        }
    }]);

myApp.directive('globalMessage', ['globalMessageService',
    function (globalMessageService) {
        return {
            templateUrl: '/src/globalMessage.html',
            scope: {},
            link: function (scope, elem, attrs) {
                globalMessageService.setUpdateListener(function (messages) {
                    scope.$apply(function () {
                        scope.messages = messages;
                    });
                });
                scope.close = function(message){
                    console.log('close');
                    globalMessageService.removeMessage(message);
                }



                setTimeout(function(){
                    globalMessageService.addDanger('my danger!!!');
                }, 10);
                setTimeout(function () {
                    globalMessageService.addDanger('my danger 2 !!!')
                }, 3000);
                setTimeout(function () {
                    globalMessageService.addDanger('my danger 3 !!!')
                }, 5000);



//                scope.messages = globalMessageService.getMessages();
            }
        }
    }]);

myApp.service('taskService', function () {
    var _tasks = [];

    this.setTasks = function (tasks) {
        _tasks = tasks;
    };

    this.getTasks = function () {
        return _tasks;
    }
});