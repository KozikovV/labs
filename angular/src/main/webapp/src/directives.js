var directives = angular.module('directives', []);

directives.directive('loading', function(){
    return {
        templateUrl: '/src/loading.html',
        scope:{
            object: '=object',
            message: '@message'
        },
        link: function($scope, $element, $attrs, $transclude){
            console.log($scope.object);

            if(!$scope.message) $scope.message = 'Loading...';
            $scope.l = 'L...';
            console.log('M: '+$scope.message);
        }
    }
});

