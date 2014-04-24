var controllers = angular.module('controllers', ['service']);

controllers.controller('ListCtrl', ['$scope', '$http', 'Category',
    function ($scope, $http, Category) {
        $scope.categories = Category.query();
    }]);

controllers.controller('CategoryCtrl',
    ['$scope', 'Category', '$routeParams',
        function ($scope, Category, $routeParams) {
            $scope.category = Category.get({id: $routeParams.categoryId});
        }]);

controllers.controller('TestCtrl',
    ['$scope', '$routeParams', 'Test', 'taskService',
        function ($scope, $routeParams, Test, taskService) {
            $scope.test = Test.get({id: $routeParams.testId});
        }]);

controllers.controller('TaskCtrl',
    ['$scope', 'taskService',
        function ($scope, taskService) {
            console.log('TaskCtrl');
            $scope.init = function (task) {
                $scope.task = task;
            }
            $scope.check = function () {
                console.log($scope.answer);
                if ($scope.task.answer === $scope.answer) {
                    $scope.answerResult = 'right';
                } else {
                    $scope.answerResult = 'wrong';
                }
            }
        }]);

controllers.controller('CreateTestCtrl',
    ['$scope', 'Category', 'Test', '$routeParams',
        function ($scope, Category, Test, $routeParams) {
            console.log('CreateTestCtrl');
            $scope.category = Category.get({id: $routeParams.categoryId});
            $scope.test = $scope.test || {};
            $scope.test.category = $scope.test.category || {};
//            $scope.test.category.id = $scope.test.category.id || $scope.category.id;
            $scope.save = function () {
                $scope.test.category.id = $scope.test.category.id || $scope.category.id;
                console.log($scope.test);
                Test.save($scope.test, function(test){
                    console.log(test);
                });
            }
            $scope.addTask = function () {
                console.log('addTask');
                $scope.test.tasks = $scope.test.tasks || [];
                $scope.test.tasks.push({});
            }
        }]);

controllers.controller('UpdateTestCtrl',
    ['$scope', 'Test', '$routeParams',
        function($scope, Test, $routeParams){
            $scope.test = Test.get({id: $routeParams.testId});
        }]);