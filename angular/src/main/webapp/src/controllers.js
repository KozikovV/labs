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
                if($scope.task.answer === $scope.answer){
                    $scope.answerResult = 'right';
                } else {
                    $scope.answerResult = 'wrong';
                }
            }
        }])