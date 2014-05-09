var directives = angular.module('directives', []);

directives.directive('loading', function(){
    return {
        templateUrl: '/src/loading.html',
        scope:{
            object: '=object',
            customMessage: '@message'
        },
        link: function($scope, $element, $attrs, $transclude){
            $scope.message = 'Loading...';
            if($scope.customMessage) $scope.message = $scope.customMessage;
        }
    }
});

