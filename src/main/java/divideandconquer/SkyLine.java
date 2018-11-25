package divideandconquer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SkyLine {

    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> skylines = getSkylineHelper(buildings, 0, buildings.length-1);
        List<int[]> result = new ArrayList<>();

        for (int i = 0; i < skylines.size(); i++) {
            int[] currentBuilding = skylines.get(i);
            int[] previousBuilding = i != 0 ? skylines.get(i-1) : null;

            if (previousBuilding != null && previousBuilding[1] < currentBuilding[0]) {
                result.add(new int[]{previousBuilding[1], 0});
            }
            result.add(new int[]{currentBuilding[0], currentBuilding[2]});
        }

        int[] lastBuilding = skylines.get(skylines.size()-1);
        result.add(new int[]{lastBuilding[1], 0});
        return result;
    }

    private List<int[]> getSkylineHelper(int[][] buildings, int start, int end) {
        if (start > end) {
            return Collections.emptyList();
        } else if (start == end) {
            return Arrays.asList(buildings[start]);
        } else {
            int mid = start + (end - start)/2;
            List<int[]> leftResult = getSkylineHelper(buildings, start, mid);
            List<int[]> rightResult = getSkylineHelper(buildings, mid+1, end);
            return mergeSkylines(leftResult, rightResult);
        }
    }

    private List<int[]> mergeSkylines(List<int[]> leftBuildings, List<int[]> rightBuildings) {
        List<int[]> merged = new ArrayList<>();
        int i = 0, j = 0;
        while (i < leftBuildings.size() && j < rightBuildings.size()) {
            int[] leftBuilding = leftBuildings.get(i);
            int[] rightBuilding = rightBuildings.get(j);

            if (leftBuilding[1] < rightBuilding[0]) {
                merged.add(leftBuilding);
                i++;
            } else if (rightBuilding[1] < leftBuilding[0]) {
                merged.add(rightBuilding);
                j++;
            } else if (leftBuilding[0] <= rightBuilding[0]) {
                int[] indices = mergeIntersect(merged, leftBuilding, rightBuilding, i, j);
                i = indices[0];
                j = indices[1];
            } else {    // leftBuilding[0] > rightBuilding[0]
                int[] indices = mergeIntersect(merged, rightBuilding, leftBuilding, j, i);
                i = indices[1];
                j = indices[0];
            }
        }

        while (i < leftBuildings.size()) {
            merged.add(leftBuildings.get(i++));
        }

        while (j < rightBuildings.size()) {
            merged.add(rightBuildings.get(j++));
        }

        return merged;
    }

    private int[] mergeIntersect(List<int[]> merged, int[] buildingA, int[] buildingB, int startA, int startB) {
        int indexA = startA, indexB = startB;

        if (buildingA[1] <= buildingB[1]) { // a.right <= b.right
            if (buildingA[2] > buildingB[2]) {  // a.height > b.height
                if (buildingB[1] != buildingA[1]) { // a.right != b.right
                    merged.add(buildingA);
                    indexA++;
                    buildingB[0] = buildingA[1];
                } else {
                    indexB++;
                }
            } else if (buildingA[2] == buildingB[2]) {  // a.height == b.height
                buildingB[0] = buildingA[0];
                indexA++;
            } else {    //  a.height < b.height
                if (buildingA[0] != buildingB[0]) {
                    merged.add(new int[]{buildingA[0], buildingB[0], buildingA[2]});
                }
                indexA++;
            }
        } else {    // a.right > b.right
            if (buildingA[2] >= buildingB[2]) { // a.height >= b.height
                indexB++;   // skip buildingB
            } else {
                if (buildingA[0] != buildingB[0]) { // a.left != b.left
                    merged.add(new int[]{buildingA[0], buildingB[0], buildingA[2]});
                }
                buildingA[0] = buildingB[1];
                merged.add(buildingB);
                indexB++;
            }
        }

        return new int[]{indexA, indexB};
    }

    public static void main(String[] args) {
        SkyLine skyLine = new SkyLine();
        int[][] buildings = {
                {2, 9, 10},
                {3, 7, 15},
                {5, 12, 12},
                {15, 20, 10},
                {19, 24, 8}
        };
        List<int[]> skyline = skyLine.getSkyline(buildings);
        for (int[] building : skyline) {
            System.out.println(Arrays.toString(building));
        }
    }
}
